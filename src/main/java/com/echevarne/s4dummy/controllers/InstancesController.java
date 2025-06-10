package com.echevarne.s4dummy.controllers;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.ListTimeSeriesRequest;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.ProjectName;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;

import lombok.extern.slf4j.Slf4j;
import com.google.protobuf.util.Timestamps;

@RestController
@RequestMapping("/instances")
@Slf4j
public class InstancesController {
	
	@GetMapping(value = "/getNumInstances/{micro}")
	public ResponseEntity<String> getNumInstances(@PathVariable("micro") String micro) {
		String response = null;
		try (MetricServiceClient client = MetricServiceClient.create()) {
			String projectId = "676620684522"; // o el ID del proyecto
	        String location = "europe-southwest1";
            String filter = String.format(
                "metric.type=\"run.googleapis.com/container/instance_count\" AND " +
                "resource.label.\"service_name\"=\"%s\" AND resource.label.\"location\"=\"%s\"",
                micro, location
            );

            long nowMillis = Instant.now().toEpochMilli();
            TimeInterval interval = TimeInterval.newBuilder()
                    .setEndTime(Timestamps.fromMillis(nowMillis))
                    .setStartTime(Timestamps.fromMillis(nowMillis - 60 * 1000)) // últimos 60 seg
                    .build();

            ListTimeSeriesRequest request = ListTimeSeriesRequest.newBuilder()
                    .setName(ProjectName.of(projectId).toString())
                    .setFilter(filter)
                    .setInterval(interval)
                    .setView(ListTimeSeriesRequest.TimeSeriesView.FULL)
                    .build();

            int totalActiveInstances = 0;

            for (TimeSeries ts : client.listTimeSeries(request).iterateAll()) {
                for (Point p : ts.getPointsList()) {
                    long value = p.getValue().getInt64Value();
                    totalActiveInstances += value;
                }
            }
            response = "Instancias activas de " + micro + ": " + totalActiveInstances;
            log.info(response);
        } catch (Exception e) {
			log.error("Error: " + e, e);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

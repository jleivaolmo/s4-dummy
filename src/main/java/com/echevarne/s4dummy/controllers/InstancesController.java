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
	
	@GetMapping(value = "/getNumInstances/{micro}/{tiempo}")
	public ResponseEntity<String> getNumInstances(@PathVariable("micro") String micro, @PathVariable("tiempo") Long tiempo) {
		String response = null;
		try (MetricServiceClient client = MetricServiceClient.create()) {
			String projectId = "pj-ma-host-prod"; // o el ID del proyecto
	        String location = "europe-southwest1";
	        //String filter = "metric.type=\"run.googleapis.com/container/instance_count\"";
			String filter = String.format("metric.type=\"run.googleapis.com/container/instance_count\" AND " + 
											"resource.label.\"service_name\"=\"%s\"", micro);
            long nowMillis = Instant.now().toEpochMilli();
            TimeInterval interval = TimeInterval.newBuilder()
                    .setEndTime(Timestamps.fromMillis(nowMillis))
                    .setStartTime(Timestamps.fromMillis(nowMillis - tiempo * 1000)) 
                    .build();
            ListTimeSeriesRequest request = ListTimeSeriesRequest.newBuilder()
                    .setName(ProjectName.of(projectId).toString())
                    .setFilter(filter)
                    .setInterval(interval)
                    .setView(ListTimeSeriesRequest.TimeSeriesView.FULL)
                    .build();

            int totalActiveInstances = 0;
            int counter = 0;
            int counter2 = 0;
            for (TimeSeries ts : client.listTimeSeries(request).iterateAll()) {
                for (Point p : ts.getPointsList()) {
                    long value = p.getValue().getInt64Value();
                    totalActiveInstances += value;
                    counter2 ++;
                }
                counter ++;
            }
            response = "Instancias de " + micro + ": " + totalActiveInstances + " Contador1: " + counter + " Contador2: " + counter2;
            log.info(response);
        } catch (Exception e) {
			log.error("Error: " + e, e);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

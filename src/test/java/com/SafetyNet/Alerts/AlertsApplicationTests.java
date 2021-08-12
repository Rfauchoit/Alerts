package com.SafetyNet.Alerts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.SafetyNet.Alerts.controller.ChildAlertController;

@SpringBootTest
class AlertsApplicationTests {

	@Autowired
	private ChildAlertController childAlertController;
	@Test
	void contextLoads() {
		assertThat(childAlertController).isNotNull();
	}

}

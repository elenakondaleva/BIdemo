package com.jedox.bidemo.application;


import org.apache.http.entity.ContentType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import us.abstracta.jmeter.javadsl.JmeterDsl;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import java.io.IOException;
import java.time.Duration;

import static com.jedox.bidemo.application.model.DimensionController.V1_DIMENSION_URL;
import static com.jedox.bidemo.application.model.DataCubeController.V1_DATACUBE_URL;

@ActiveProfiles("performance")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DimensionControllerLoadTest {

    private static final String URL = "http://localhost:8080" + V1_DIMENSION_URL;
    private static final String URL_DIMENSION_HIERARCHY = URL + "/0/75/hierarchy";

    private static final String URL_CUBE = "http://localhost:8080" + V1_DATACUBE_URL;
    private static final String URL_CUBE_DATA = URL_CUBE + "/-1/-1/-1/data";

    @Test
    void getDimensionHierarchy() throws IOException {
        DslHttpSampler sampler = JmeterDsl.httpSampler(URL_DIMENSION_HIERARCHY).method("GET")
                .contentType(ContentType.APPLICATION_JSON);
        TestPlanStats stats = JmeterDsl.testPlan(JmeterDsl.threadGroup(2, 10, sampler)).run();

        MatcherAssert.assertThat(stats.overall().errorsCount(), Matchers.is(0L));
        MatcherAssert.assertThat(stats.overall().sampleTimePercentile99(), Matchers.lessThan(Duration.ofSeconds(1)));
    }

    @Test
    void getDatacubeData() throws IOException {
        DslHttpSampler sampler = JmeterDsl.httpSampler(URL_CUBE_DATA).method("GET")
                .contentType(ContentType.APPLICATION_JSON);
        TestPlanStats stats = JmeterDsl.testPlan(JmeterDsl.threadGroup(2, 10, sampler)).run();

        MatcherAssert.assertThat(stats.overall().errorsCount(), Matchers.is(0L));
        MatcherAssert.assertThat(stats.overall().sampleTimePercentile99(), Matchers.lessThan(Duration.ofSeconds(1)));
    }
}

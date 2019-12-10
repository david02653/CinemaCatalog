//package cinemacatalog;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
//import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
//import org.springframework.test.context.junit4.SpringRunner;
//
///* @AutoConfigureStubRunner - stubsMode
//* Use the REMOTE stubsMode when downloading stubs from an online repository and LOCAL for offline work
//*
//* @AutoConfigureStubRunner - ids
//* ids = "com.baeldung.spring.cloud:spring-cloud-contract-producer:+:stubs:8090"
//* com.baeldung.spring.cloud — the groupId of our artifact (?
//* spring-cloud-contract-producer — the artifactId of the producer stub jar
//* 8090 — the port on which the generated stubs will run*/
//
//
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//
////@AutoConfigureJsonTesters
////@AutoConfigureStubRunner(
////        ids = {"cinemacatalog:person-service:+:stubs:8090"},
////        consumerName = "cinemacatalog",
////        stubsPerConsumer = true,
////        stubsMode = StubRunnerProperties.StubsMode.Remote,
////        repositoryRoot = "http://192.168.99.100:8081/artifactory/libs-snapshot-local")
//
//
////@AutoConfigureStubRunner(
////        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
////        ids = "com.baeldung.spring.cloud:spring-cloud-contract-producer:+:stubs:8090")
//
////@AutoConfigureMockMvc
////@DirtiesContext
//
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
////@AutoConfigureStubRunner(
////        ids = {"Ordering:Ordering:+:stubs:10001"},
////        consumerName = "cinemacatalog",
////        stubsPerConsumer = true,
////        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@AutoConfigureStubRunner(
//        ids = {"Ordering:Ordering:+:stubs:10001"},
//        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//public class BasicControllerTest {
//    @Autowired
//    private CinemaCatalogController cinemaCatalogController;
//
//    @Test
//    public void verifyController() {
////        Person p = personClient.findPersonById(1);
//        String s = cinemaCatalogController.index();
//        Assert.assertNotNull(s);
////        Assert.assertEquals(1, p.getId().intValue());
////        Assert.assertNotNull(p.getFirstName());
////        Assert.assertNotNull(p.getLastName());
////        Assert.assertNotNull(p.getAddress());
////        Assert.assertNotNull(p.getAddress().getCity());
////        Assert.assertNotNull(p.getAddress().getCountry());
////        Assert.assertNotNull(p.getAddress().getPostalCode());
////        Assert.assertNotNull(p.getAddress().getStreet());
////        Assert.assertNotEquals(0, p.getAddress().getHouseNo());
//    }
//}

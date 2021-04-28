package org.eclipse.che;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import junit.framework.AssertionFailedError;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** Unit test for simple App. */
public class AppTest {
  private KubernetesServer serverMock;
  @BeforeClass
  public void setUpLogger() throws Exception {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
  }

  @BeforeMethod
  public void setUp() throws Exception {

    serverMock = new KubernetesServer(true, true);
    serverMock.before();
  }


  @AfterMethod
  public void tearDown() {

    serverMock.after();
  }

  @Test
  public void testInCrudMode() throws InterruptedException {
    KubernetesClient client = serverMock.getClient();

    //CREATE
    client.pods().inNamespace("ns1").create(new PodBuilder().withNewMetadata().withName("pod1").endMetadata().build());

    //READ
    PodList podList = client.pods().inNamespace("ns1").list();
    Assert.assertNotNull(podList);
    Assert.assertEquals(1, podList.getItems().size());

  }
}

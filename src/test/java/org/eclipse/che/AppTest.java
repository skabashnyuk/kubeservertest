package org.eclipse.che;

import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
  public void shouldAnswerWithTrue() {
    Assert.assertTrue(true);
  }
}

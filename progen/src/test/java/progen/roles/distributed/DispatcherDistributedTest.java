package progen.roles.distributed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import progen.context.ProGenContext;
import progen.roles.Dispatcher;
import progen.roles.ProGenFactory;

public class DispatcherDistributedTest {

  private Dispatcher dispatcher;

  @Before
  public void setUp() throws Exception {
    ProGenContext.makeInstance();
    ProGenContext.setProperty("progen.roles.factory", "distributed");
    ProGenContext.setProperty("progen.role.client.dispatcher.bindAddress", getDefaultBindAddress());
    ProGenContext.setProperty("progen.role.client.dispatcher.port", "1088");
  }

  private String getDefaultBindAddress() {
    String address = "127.0.0.1";
    try {
      InetAddress addr = InetAddress.getLocalHost();
      address = addr.getHostName();
    } catch (UnknownHostException e) {
      //do nothing
    }
    return address;
  }

  @After
  public void tearDown() throws Exception {
    ProGenContext.clearContext();
    clearSingleton();
  }

  private void clearSingleton() {
    Field factoryField;
    try {
      factoryField = ProGenFactory.class.getDeclaredField("factory");
      factoryField.setAccessible(true);
      factoryField.set(ProGenFactory.makeInstance(), null);
    } catch (SecurityException e) {
      fail(e.getMessage());
    } catch (IllegalArgumentException e) {
      fail(e.getMessage());
    } catch (IllegalAccessException e) {
      fail(e.getMessage());
    } catch (NoSuchFieldException e) {
      fail(e.getMessage());
    }
  }

  @Test@Ignore
  public void testDispatcherDistributed() {
    dispatcher = ProGenFactory.makeInstance().makeDispatcher();
    assertEquals(3, dispatcher.totalTasksDone());
  }

}
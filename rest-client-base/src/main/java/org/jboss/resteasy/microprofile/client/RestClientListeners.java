package org.jboss.resteasy.microprofile.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.WeakHashMap;

import org.eclipse.microprofile.rest.client.spi.RestClientListener;

public class RestClientListeners
{

   private RestClientListeners()
   {
   }

   /**
    * A synchronized weak hash map that keeps RestClientListener instances retrieved using ServiceLoader for each classloader.
    * Weak keys are used to remove entries when classloaders are garbage collected.
    */
   private static final Map<ClassLoader, Collection<RestClientListener>> map = Collections
         .synchronizedMap(new WeakHashMap<>());

   public static Collection<RestClientListener> get()
   {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      Collection<RestClientListener> c;
      c = map.get(loader);
      if (c == null) {
         c = new ArrayList<>();
         ServiceLoader.load(RestClientListener.class, loader).forEach(c::add);
         map.put(loader, Collections.unmodifiableCollection(c));
      }
      return c;
   }
}

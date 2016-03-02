/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.furnace.util;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Stream utilities.
 *
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public abstract class Streams
{
   /**
    * Return a {@link String} containing the contents of the given {@link InputStream}
    */
   public static String toString(final InputStream stream)
   {
      StringBuilder out = new StringBuilder();
      try (Reader in = new InputStreamReader(stream, "UTF-8"))
      {
         final char[] buffer = new char[8192];
         int read;
         do
         {
            read = in.read(buffer, 0, buffer.length);
            if (read > 0)
            {
               out.append(buffer, 0, read);
            }
         }
         while (read >= 0);
      }
      catch (UnsupportedEncodingException e)
      {
         throw new RuntimeException(e);
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
      return out.toString();
   }

   /**
    * Return a {@link String} containing the contents of the given {@link InputStream}
    */
   public static String toString(final InputStream stream, Charset charset)
   {
      StringBuilder out = new StringBuilder();
      try (Reader in = new InputStreamReader(stream, charset))
      {
         final char[] buffer = new char[8192];
         int read;
         do
         {
            read = in.read(buffer, 0, buffer.length);
            if (read > 0)
            {
               out.append(buffer, 0, read);
            }
         }
         while (read >= 0);
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
      return out.toString();
   }

   public static void write(final InputStream source, final OutputStream destination)
   {
      try (InputStream is = source)
      {
         final byte[] buffer = new byte[8192];
         int read;
         do
         {
            read = is.read(buffer, 0, buffer.length);
            if (read > 0)
            {
               destination.write(buffer, 0, read);
            }
         }
         while (read >= 0);
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

   public static InputStream fromString(final String data)
   {
      return new ByteArrayInputStream(data.getBytes());
   }

   /**
    * Closes the resource without throwing any exception
    *
    * @param source the resource to be closed. May be null
    */
   public static void closeQuietly(final Closeable source)
   {
      if (source != null)
      {
         try
         {
            source.close();
         }
         catch (IOException ignore)
         {

         }
      }
   }
}

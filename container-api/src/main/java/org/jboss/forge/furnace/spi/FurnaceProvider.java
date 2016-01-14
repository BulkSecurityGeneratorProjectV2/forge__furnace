/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.furnace.spi;

import org.jboss.forge.furnace.Furnace;

/**
 * Returns the associated Furnace from a Furnace container
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public interface FurnaceProvider
{
   Furnace getFurnace(ClassLoader loader);
}

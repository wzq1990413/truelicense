/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package net.java.truelicense.swing;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.Immutable;
import javax.swing.*;
import net.java.truelicense.core.LicenseConsumerManager;
import net.java.truelicense.swing.util.Enabler;

/**
 * A decorating license consumer manager which hosts an {@link Enabler}.
 *
 * @author Christian Schlichtherle
 */
@Immutable
abstract class UpdatingLicenseConsumerManager
extends DecoratingLicenseConsumerManager
implements Serializable {

    private final Enabler enabler;

    UpdatingLicenseConsumerManager(
            final LicenseConsumerManager manager,
            final Enabler enabler) {
        super(manager);
        assert null != enabler;
        this.enabler = enabler;
    }

    final void enable() { enabled(true); }
    final void disable() { enabled(false); }

    final boolean enabled() {
        class Action implements Runnable {
            @SuppressWarnings("PackageVisibleField")
            boolean result;

            @Override public void run() {
                result = enabler.enabled();
            }
        }
        return runOnEventDispatchThread(new Action()).result;
    }

    final void enabled(final boolean value) {
        runOnEventDispatchThread(new Runnable() {
            @Override
            public void run() {
                enabler.enabled(value);
            }
        });
    }

    private <R extends Runnable> R runOnEventDispatchThread(R action) {
        if (SwingUtilities.isEventDispatchThread()) {
            action.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(action);
            } catch (InterruptedException ex) {
                action.run(); // never mind!
            } catch (InvocationTargetException ex) {
                throw new AssertionError(ex);
            }
        }
        return action;
    }
}

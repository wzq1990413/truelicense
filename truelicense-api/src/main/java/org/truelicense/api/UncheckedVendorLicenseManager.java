/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.api;

/**
 * A vendor license manager which generally throws an
 * {@link UncheckedLicenseManagementException} rather than a (checked)
 * {@link LicenseManagementException}.
 *
 * @see UncheckedManager#from(VendorLicenseManager)
 * @author Christian Schlichtherle
 */
public interface UncheckedVendorLicenseManager extends VendorLicenseManager {

    /** Returns the underlying (checked) vendor license manager. */
    VendorLicenseManager checked();

    @Override
    UncheckedLicenseKeyGenerator generator(License bean);
}
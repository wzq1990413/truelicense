/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.it.v1

import org.truelicense.it.core.CodecTestSuite
import org.junit.runner._
import org.scalatest.junit._
import org.truelicense.it.v1.V1EncryptionTestContext

/** @author Christian Schlichtherle */
@RunWith(classOf[JUnitRunner])
class V1EncryptionTest
extends CodecTestSuite
   with V1TestContext
   with V1EncryptionTestContext

/*
 *
 *  The contents of this file are subject to the Terracotta Public License Version
 *  2.0 (the "License"); You may not use this file except in compliance with the
 *  License. You may obtain a copy of the License at
 *
 *  http://terracotta.org/legal/terracotta-public-license.
 *
 *  Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 *  the specific language governing rights and limitations under the License.
 *
 *  The Covered Software is Connection API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.connection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URI;
import java.net.URISyntaxException;

public class URIUtilsTest {
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void allowSingleServerURI() throws Exception {
    URI uri = new URI("terracotta://server:1234");
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void allowSingleServerURIWithSlash() throws Exception {
    URI uri = new URI("terracotta://server:1234/");
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void preventSingleServerURIWithPath() throws Exception {
    URI uri = new URI("terracotta://server:1234/path");
    expectedException.expect(URISyntaxException.class);
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void preventSingleServerURIWithEmptyQuery() throws Exception {
    URI uri = new URI("terracotta://server:1234?");
    expectedException.expect(URISyntaxException.class);
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void preventSingleServerURIWithQuery() throws Exception {
    URI uri = new URI("terracotta://server:1234?abc=def");
    expectedException.expect(URISyntaxException.class);
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void preventSingleServerURIWithFragment() throws Exception {
    URI uri = new URI("terracotta://server:1234#fragment");
    expectedException.expect(URISyntaxException.class);
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void allowMultiServerURI() throws Exception {
    URI uri = new URI("terracotta://server1:1234,server2:5678");
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void allowMultiServerURIWithSlash() throws Exception {
    URI uri = new URI("terracotta://server1:1234,server2:5678/");
    URIUtils.validateTerracottaURI(uri);
  }

  @Test
  public void preventMultiServerURIWithPath() throws Exception {
    URI uri = new URI("terracotta://server1:1234,server2:5678/path");
    expectedException.expect(URISyntaxException.class);
    URIUtils.validateTerracottaURI(uri);
  }
}

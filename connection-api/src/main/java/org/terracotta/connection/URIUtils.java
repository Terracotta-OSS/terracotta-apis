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

import java.net.URI;
import java.net.URISyntaxException;

final class URIUtils {
  private URIUtils() {}

  static void validateTerracottaURI(URI uri) throws URISyntaxException {
    String path = uri.getPath();
    if (path != null && path.length() > 0 && !path.equals("/")) {
      throw new URISyntaxException(uri.toString(), "A path should not be specified in a Terracotta URI");
    }

    String query = uri.getQuery();
    if (query != null) {
      throw new URISyntaxException(uri.toString(), "A query should not be specified in a Terracotta URI");
    }

    String fragment = uri.getFragment();
    if (fragment != null) {
      throw new URISyntaxException(uri.toString(), "A fragment should not be specified in a Terracotta URI");
    }
  }
}

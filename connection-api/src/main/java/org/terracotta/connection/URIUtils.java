/*
 * Copyright Terracotta, Inc.
 * Copyright IBM Corp. 2024, 2025
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

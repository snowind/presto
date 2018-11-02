/*
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
package com.facebook.presto.catalog;

import com.facebook.presto.metadata.DynamicCatalogStoreConfig;
import com.google.common.base.Strings;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public abstract class CatalogLoader
{
    protected final DynamicCatalogStoreConfig config;

    public CatalogLoader(DynamicCatalogStoreConfig config)
    {
        this.config = config;
    }

    public abstract Map<String, CatalogInfo> load()
            throws Exception;

    protected String replaceVariable(String origin)
            throws UnknownHostException
    {
        if (!Strings.isNullOrEmpty(origin)) {
            String hostname = InetAddress.getLocalHost().getHostName();
            return origin.replaceAll("_HOST", hostname);
        }
        return origin;
    }
}

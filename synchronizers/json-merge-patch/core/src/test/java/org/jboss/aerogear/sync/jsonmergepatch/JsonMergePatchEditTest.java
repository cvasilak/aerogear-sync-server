/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.sync.jsonmergepatch;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jboss.aerogear.sync.jsonmergepatch.Patches.*;

public class JsonMergePatchEditTest {

    @Test (expected = NullPointerException.class)
    public void constructWithNullPatch() {
        JsonMergePatchEdit.withPatch(null).build();
    }

    @Test (expected = NullPointerException.class)
    public void constructWithNullChecksum() {
        JsonMergePatchEdit.withPatch(objectNode("Fletch")).checksum(null).build();
    }

    @Test
    public void createJsonPatchEdit() throws Exception {
        final ObjectNode jsonNode = objectNode("Fletch");
        final JsonMergePatchEdit edit = JsonMergePatchEdit.withPatch(jsonNode).checksum("123").build();
        assertThat(edit.diff(), is(notNullValue()));
        assertThat(edit.diff(), equalTo(JsonMergePatchDiff.fromJsonNode(jsonNode)));
    }

    @Test
    public void equalsReflexsive() throws Exception {
        final JsonMergePatchEdit x = newJsonMergePatchEdit("Fletch");
        assertThat(x, equalTo(x));
    }

    @Test
    public void equalsSymmetric() throws Exception {
        final JsonMergePatchEdit x = newJsonMergePatchEdit("Fletch");
        final JsonMergePatchEdit y = newJsonMergePatchEdit("Fletch");
        assertThat(x, equalTo(y));
        assertThat(y, equalTo(x));
    }

    @Test
    public void equalsTransitive() {
        final JsonMergePatchEdit x = newJsonMergePatchEdit("Fletch");
        final JsonMergePatchEdit y = newJsonMergePatchEdit("Fletch");
        final JsonMergePatchEdit z = newJsonMergePatchEdit("Fletch");
        assertThat(x, equalTo(y));
        assertThat(y, equalTo(z));
        assertThat(x, equalTo(z));
    }

    @Test
    public void equalsNull() {
        final JsonMergePatchEdit x = newJsonMergePatchEdit("Fletch");
        assertThat(x.equals(null), is(false));
    }

    @Test
    public void nonEqual() {
        final JsonMergePatchEdit x = newJsonMergePatchEdit("Fletch");
        final JsonMergePatchEdit y = JsonMergePatchEdit.withPatch(objectNode("fletch")).checksum("123").build();
        assertThat(x.equals(y), is(false));
    }

}
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
package org.jboss.aerogear.sync.diffmatchpatch;

import org.jboss.aerogear.sync.Diff;
import org.jboss.aerogear.sync.util.Arguments;

public class DiffMatchPatchDiff implements Diff {

    public enum Operation { DELETE, ADD, UNCHANGED }

    private final Operation operation;
    private final String text;

    public DiffMatchPatchDiff(final Operation operation, final String text) {
        this.operation = Arguments.checkNotNull(operation, "operation must not be null");
        this.text = Arguments.checkNotNull(text, "text must not be null");
    }

    public Operation operation() {
        return operation;
    }

    public String text() {
        return text;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DiffMatchPatchDiff that = (DiffMatchPatchDiff) o;
        if (operation != that.operation) {
            return false;
        }
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        int result = operation.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DiffMatchPatchDiff[operation=" + operation + ", text=" + text + ']';
    }
}

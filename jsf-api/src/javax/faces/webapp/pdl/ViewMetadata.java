/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.faces.webapp.pdl;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIViewParameter;
import javax.faces.component.UIViewRoot;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * RELEASE_PENDING (edburns,rogerk) review docs
 *
 * <p class="changed_added_2_0"/>
 * <code>ViewMetadata</code> is reponsible for extracting and providing
 * view parameter metadata from PDL views.
 * </p>
 *
 * @since 2.0
 */
public abstract class ViewMetadata {


    /**
     * <p class="changed_added_2_0"/>
     * @return the view ID for which this <code>ViewMetadata</code> instance
     *  was created
     */
    public abstract String getViewId();


    /**
     * <p class="changed_added_2_0">
     * Creates a new {@link UIViewRoot} containing
     * only view parameter metadata.  The processing of building this
     * <code>UIViewRoot</code> with metadata should not cause any events to be
     * published to the application.
     * </p>
     *
     * @param context the {@link FacesContext} for the current request
     * @return a <code>UIViewRoot</code> containing only view parameter metadata
     *  (if any)
     */
    public abstract UIViewRoot createMetadataView(FacesContext context);


    /**
     * <p class="changed_added_2_0">
     * Returns the view parameter metadata for the view id for which this
     * <code>ViewMetadata</code> instance was created.
     * </p>
     * @param context the {@link FacesContext} for the current request
     * @return a <code>Collection</code> of {@link UIViewParameter} instances
     *  (if any) from the view associated with this <code>ViewMetadata</code>
     *  instance
     */
    public abstract Collection<UIViewParameter> getViewParameters(FacesContext context);


    /**
     * <p class="changed_added_2_0">
     * Utility method to extract view metadata from the provided {@link UIViewRoot}.
     * </p>
     *
     * @param root the {@link UIViewRoot} from which the metadata will be extracted
     *  from
     *
     * @return a <code>Collection</code> of {@link UIViewParameter} instances.  If
     *  the view has no metadata, the collection will be empty.
     */
    public static Collection<UIViewParameter> getViewParameters(UIViewRoot root) {

        Collection<UIViewParameter> params;
        UIComponent metadataFacet = root.getFacet(UIViewRoot.METADATA_FACET_NAME);

        if (metadataFacet == null) {
            params = Collections.emptyList();
        } else {
            params = new ArrayList<UIViewParameter>();
            List<UIComponent> children = metadataFacet.getChildren();
            int len = children.size();
            for (int i = 0; i < len; i++) {
                UIComponent c = children.get(i);
                if (c instanceof UIViewParameter) {
                    params.add((UIViewParameter) c);
                }
            }
        }

        return params;

    }


}

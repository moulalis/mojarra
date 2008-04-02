/*
 * $Id: UISelectItems.java,v 1.17 2003/10/09 19:18:12 craigmcc Exp $
 */

/*
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.faces.component;


import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 * <p><strong>UISelectItems</strong> is a component that may be nested
 * inside a {@link UISelectMany} or {@link UISelectOne} component, and
 * causes the addition of one or more {@link SelectItem} instances to the
 * list of available options in the parent component.  The
 * <code>value</code> of this component (set either directly, or acquired
 * indirectly via the <code>valueRef</code> property, can be of any
 * of the following types:</p>
 * <ul>
 * <li><em>Single instance of {@link SelectItem}</em> - This instance is
 *     added to the set of available options for the parent tag.</li>
 * <li><em>Array of {@link SelectItem}</em> - This set of instances is
 *     added to the set of available options for the parent component,
 *     in ascending subscript order.</li>
 * <li><em>Collection of {@link SelectItem}</em> - This set of instances is
 *     added to the set of available options for the parent component,
 *     in the order provided by an iterator over them.</li>
 * <li><em>Map</em> - The keys of this object (once converted to
 *     Strings) are assumed to be labels, and the values of this object
 *     (once converted to Strings)
 *     are assumed to be values, of {@link SelectItem} instances that will
 *     be constructed dynamically and added to the set of available options
 *     for the parent component, in the order provided by an iterator over
 *     the keys.</li>
 * </ul>
 */

public class UISelectItems extends UIComponentBase implements ValueHolder {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Create a new {@link UISelectItems} instance with default property
     * values.</p>
     */
    public UISelectItems() {

        super();
        setRendererType(null);

    }


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>The {@link ValueHolderSupport} instance to which we delegate
     * our {@link ValueHolder} implementation processing.</p>
     */
    private ValueHolderSupport support = new ValueHolderSupport(this);



    // -------------------------------------------------- ValueHolder Properties


    public Object getValue() {

        return (support.getValue());

    }


    public void setValue(Object value) {

        support.setValue(value);

    }


    public String getValueRef() {

        return (support.getValueRef());

    }


    public void setValueRef(String valueRef) {

        support.setValueRef(valueRef);

    }


    // ----------------------------------------------------- ValueHolder Methods

    /**
     * @exception EvaluationException {@inheritDoc}
     * @exception NullPointerException {@inheritDoc}  
     */
    public Object currentValue(FacesContext context) {

        return (support.currentValue(context));

    }


    // ----------------------------------------------------- StateHolder Methods


    public Object saveState(FacesContext context) {

        Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = saveAttachedState(context, support);
        return (values);

    }


    public void restoreState(FacesContext context, Object state)
        throws IOException {

        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
	support = (ValueHolderSupport) restoreAttachedState(context, values[1]);
	support.setComponent(this);

    }




}

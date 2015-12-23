package com.vaadin.ui;

import com.facebook.infer.models.InferUndefined;

class AbstractSelect{

    boolean isNullSelectionAllowed = true;
    Object nullSelection = null;
    Object selection = null;


    public Object getValue() {
        Object v = InferUndefined.object_undefined();
        if(isNullSelectionAllowed){
            boolean choice = InferUndefined.boolean_undefined();
            if(choice){
                return v;
            }else{
                return null;
            }
        }else{
            boolean choice = InferUndefined.boolean_undefined();
            if(choice){
                return v;
            }else{
                return nullSelection;
            }
        }
    }

    public boolean isNullSelectionAllowed() {
        return isNullSelectionAllowed;
    }

    public Object getNullSelectionItemId(){
        return nullSelection;
    } 

    public void setNullSelectionItemId(Object nullSelectionItemId){
        nullSelection = nullSelectionItemId;
    }

    public void setValue(Object newValue){
        if(isNullSelectionAllowed){
            newValue.hashCode();
        }
        selection = newValue;
    }

    public void setNullSelectionAllowed(boolean nullSelectionAllowed){
        isNullSelectionAllowed = nullSelectionAllowed;
    }
}
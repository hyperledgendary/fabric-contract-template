/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */

package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class {{asset }} {

    private final static Genson genson = new Genson();

    @Property()
    private String value;

    public {{asset }}(){
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static {{asset }} fromJSONString(String json) {
        {{asset }} asset = genson.deserialize(json, {{asset }}.class);
        return asset;
    }
}

/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */

package org.example

import org.hyperledger.fabric.contract.annotation.DataType
import org.hyperledger.fabric.contract.annotation.Property
import org.json.JSONObject

@DataType
class {{asset }}(@Property() var value: String?) {

    fun toJSONString(): String {
        return JSONObject(this).toString()
    }

    companion object {
        fun fromJSONString(json: String): {{asset }} {
            val value = JSONObject(json).getString("value")
            return {{asset }}(value)
        }
    }
    
}
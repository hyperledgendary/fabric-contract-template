/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */

package org.example

import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.contract.ContractInterface
import org.hyperledger.fabric.contract.annotation.Contact
import org.hyperledger.fabric.contract.annotation.Contract
import org.hyperledger.fabric.contract.annotation.Default
import org.hyperledger.fabric.contract.annotation.Info
import org.hyperledger.fabric.contract.annotation.License
import org.hyperledger.fabric.contract.annotation.Transaction

@Contract(name = "{{asset }}Contract",
    info = Info(title = "{{asset }} contract",
                description = "{{description }}",
                version = "{{version }}",
                license =
                        License(name = "{{license }}",
                                url = ""),
                                contact = Contact(email = "{{name }}@example.com",
                                                  name = "{{name }}",
                                                  url = "http://{{name }}.me")))
@Default
class {{asset }}Contract : ContractInterface {

    @Transaction
    fun {{assetCamelCase }}Exists(ctx: Context, {{assetCamelCase }}Id: String): Boolean {
        val buffer = ctx.stub.getState({{assetCamelCase }}Id)
        return (buffer != null && buffer.size > 0)
    }

    @Transaction
    fun create{{asset }}(ctx: Context, {{assetCamelCase }}Id: String, value: String) {
        val exists = {{assetCamelCase }}Exists(ctx, {{assetCamelCase }}Id)
        if (exists) {
            throw RuntimeException("The {{assetSpaceSeparator }} ${{assetCamelCase }}Id already exists")
        }
        val asset = {{asset }}(value)
        ctx.stub.putState({{assetCamelCase }}Id, asset.toJSONString().toByteArray(Charsets.UTF_8))
    }

    @Transaction
    fun read{{asset }}(ctx: Context, {{assetCamelCase }}Id: String): {{asset }} {
        val exists = {{assetCamelCase }}Exists(ctx, {{assetCamelCase }}Id)
        if (!exists) {
            throw RuntimeException("The {{assetSpaceSeparator }} ${{assetCamelCase }}Id does not exist")
        }
        return {{asset }}.fromJSONString(ctx.stub.getState({{assetCamelCase }}Id).toString(Charsets.UTF_8))
    }

    @Transaction
    fun update{{asset }}(ctx: Context, {{assetCamelCase }}Id: String, newValue: String) {
        val asset = read{{asset }}(ctx, {{assetCamelCase }}Id)
        asset.value = newValue
        ctx.stub.putState({{assetCamelCase }}Id, asset.toJSONString().toByteArray(Charsets.UTF_8))
    }

    @Transaction
    fun delete{{asset }}(ctx: Context, {{assetCamelCase }}Id: String) {
        val exists = {{assetCamelCase }}Exists(ctx, {{assetCamelCase }}Id)
        if (!exists) {
            throw RuntimeException("The {{assetSpaceSeparator }} ${{assetCamelCase }}Id does not exist")
        }
        ctx.stub.delState({{assetCamelCase }}Id)
    }

}

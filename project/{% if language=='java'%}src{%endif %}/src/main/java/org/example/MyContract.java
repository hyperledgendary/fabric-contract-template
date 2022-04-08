/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */
package org.example;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import static java.nio.charset.StandardCharsets.UTF_8;

@Contract(name = "{{asset }}Contract",
    info = @Info(title = "{{asset }} contract",
                description = "{{description }}",
                version = "{{version }}",
                license =
                        @License(name = "{{license }}",
                                url = ""),
                                contact =  @Contact(email = "{{name }}@example.com",
                                                name = "{{name }}",
                                                url = "http://{{name }}.me")))
@Default
public class {{asset }}Contract implements ContractInterface {
    public  {{asset }}Contract() {

    }
    @Transaction()
    public boolean {{assetCamelCase }}Exists(Context ctx, String {{assetCamelCase }}Id) {
        byte[] buffer = ctx.getStub().getState({{assetCamelCase }}Id);
        return (buffer != null && buffer.length > 0);
    }

    @Transaction()
    public void create{{asset }}(Context ctx, String {{assetCamelCase }}Id, String value) {
        boolean exists = {{assetCamelCase }}Exists(ctx,{{assetCamelCase }}Id);
        if (exists) {
            throw new RuntimeException("The asset "+{{assetCamelCase }}Id+" already exists");
        }
        {{asset }} asset = new {{asset }}();
        asset.setValue(value);
        ctx.getStub().putState({{assetCamelCase }}Id, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public {{asset }} read{{asset }}(Context ctx, String {{assetCamelCase }}Id) {
        boolean exists = {{assetCamelCase }}Exists(ctx,{{assetCamelCase }}Id);
        if (!exists) {
            throw new RuntimeException("The asset "+{{assetCamelCase }}Id+" does not exist");
        }

        {{asset }} newAsset = {{asset }}.fromJSONString(new String(ctx.getStub().getState({{assetCamelCase }}Id),UTF_8));
        return newAsset;
    }

    @Transaction()
    public void update{{asset }}(Context ctx, String {{assetCamelCase }}Id, String newValue) {
        boolean exists = {{assetCamelCase }}Exists(ctx,{{assetCamelCase }}Id);
        if (!exists) {
            throw new RuntimeException("The asset "+{{assetCamelCase }}Id+" does not exist");
        }
        {{asset }} asset = new {{asset }}();
        asset.setValue(newValue);

        ctx.getStub().putState({{assetCamelCase }}Id, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public void delete{{asset }}(Context ctx, String {{assetCamelCase }}Id) {
        boolean exists = {{assetCamelCase }}Exists(ctx,{{assetCamelCase }}Id);
        if (!exists) {
            throw new RuntimeException("The asset "+{{assetCamelCase }}Id+" does not exist");
        }
        ctx.getStub().delState({{assetCamelCase }}Id);
    }

}

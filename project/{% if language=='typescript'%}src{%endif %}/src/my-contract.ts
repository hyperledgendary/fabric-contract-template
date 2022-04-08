/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */

import { Context, Contract, Info, Returns, Transaction } from 'fabric-contract-api';
import { {{asset }} } from './{{assetDashSeparator }}';

@Info({title: '{{asset }}Contract', description: '{{description }}' })
export class {{asset }}Contract extends Contract {

    @Transaction(false)
    @Returns('boolean')
    public async {{assetCamelCase }}Exists(ctx: Context, {{assetCamelCase }}Id: string): Promise<boolean> {
        const data: Uint8Array = await ctx.stub.getState({{assetCamelCase }}Id);
        return (!!data && data.length > 0);
    }

    @Transaction()
    public async create{{asset }}(ctx: Context, {{assetCamelCase }}Id: string, value: string): Promise<void> {
        const exists: boolean = await this.{{assetCamelCase }}Exists(ctx, {{assetCamelCase }}Id);
        if (exists) {
            throw new Error(`The {{assetSpaceSeparator }} ${{{assetCamelCase }}Id} already exists`);
        }
        const {{assetCamelCase }}: {{asset }} = new {{asset }}();
        {{assetCamelCase }}.value = value;
        const buffer: Buffer = Buffer.from(JSON.stringify({{assetCamelCase }}));
        await ctx.stub.putState({{assetCamelCase }}Id, buffer);
    }

    @Transaction(false)
    @Returns('{{asset }}')
    public async read{{asset }}(ctx: Context, {{assetCamelCase }}Id: string): Promise<{{asset }}> {
        const exists: boolean = await this.{{assetCamelCase }}Exists(ctx, {{assetCamelCase }}Id);
        if (!exists) {
            throw new Error(`The {{assetSpaceSeparator }} ${{{assetCamelCase }}Id} does not exist`);
        }
        const data: Uint8Array = await ctx.stub.getState({{assetCamelCase }}Id);
        const {{assetCamelCase }}: {{asset }} = JSON.parse(data.toString()) as {{asset }};
        return {{assetCamelCase }};
    }

    @Transaction()
    public async update{{asset }}(ctx: Context, {{assetCamelCase }}Id: string, newValue: string): Promise<void> {
        const exists: boolean = await this.{{assetCamelCase }}Exists(ctx, {{assetCamelCase }}Id);
        if (!exists) {
            throw new Error(`The {{assetSpaceSeparator }} ${{{assetCamelCase }}Id} does not exist`);
        }
        const {{assetCamelCase }}: {{asset }} = new {{asset }}();
        {{assetCamelCase }}.value = newValue;
        const buffer: Buffer = Buffer.from(JSON.stringify({{assetCamelCase }}));
        await ctx.stub.putState({{assetCamelCase }}Id, buffer);
    }

    @Transaction()
    public async delete{{asset }}(ctx: Context, {{assetCamelCase }}Id: string): Promise<void> {
        const exists: boolean = await this.{{assetCamelCase }}Exists(ctx, {{assetCamelCase }}Id);
        if (!exists) {
            throw new Error(`The {{assetSpaceSeparator }} ${{{assetCamelCase }}Id} does not exist`);
        }
        await ctx.stub.deleteState({{assetCamelCase }}Id);
    }

}

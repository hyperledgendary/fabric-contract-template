/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */

package org.example

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.nhaarman.mockitokotlin2.*

import java.util.ArrayList

import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.shim.ChaincodeStub
import org.hyperledger.fabric.shim.Chaincode.Response
import org.hyperledger.fabric.shim.Chaincode.Response.Status

class {{asset }}ContractTest {

    lateinit var ctx: Context
    lateinit var stub: ChaincodeStub

    @BeforeEach
    fun beforeEach() {
        ctx = mock()
        stub = mock()
        whenever(ctx.stub).thenReturn(stub)
        whenever(stub.getState("1001")).thenReturn("{\"value\":\"{{assetSpaceSeparator }} 1001 value\"}".toByteArray(Charsets.UTF_8))
        whenever(stub.getState("1002")).thenReturn("{\"value\":\"{{assetSpaceSeparator }} 1002 value\"}".toByteArray(Charsets.UTF_8))
    }

    @Nested
    inner class {{assetCamelCase }}Exists {

        @Test
        fun `should return true for a {{assetSpaceSeparator }}`() {
            val contract = {{asset }}Contract()
            val result = contract.{{assetCamelCase }}Exists(ctx, "1001")
            assertTrue(result)
        }

        @Test
        fun `should return false for a {{assetSpaceSeparator }} that does not exist (no key)`() {
            val contract = {{asset }}Contract()
            val result = contract.{{assetCamelCase }}Exists(ctx, "1003")
            assertFalse(result)
        }

        @Test
        fun `should return false for a {{assetSpaceSeparator }} that does not exist (no data)`() {
            val contract = {{asset }}Contract()
            whenever(stub.getState("1003")).thenReturn(ByteArray(0))
            val result = contract.{{assetCamelCase }}Exists(ctx, "1003")
            assertFalse(result)
        }

    }

    @Nested
    inner class create{{asset }} {

        @Test
        fun `should create a {{assetSpaceSeparator }}`() {
            val contract = {{asset }}Contract()
            contract.create{{asset }}(ctx, "1003", "{{assetSpaceSeparator }} 1003 value")
            verify(stub, times(1)).putState("1003", "{\"value\":\"{{assetSpaceSeparator }} 1003 value\"}".toByteArray(Charsets.UTF_8))
        }

        @Test
        fun `should throw an error for a {{assetSpaceSeparator }} that already exists`() {
            val contract = {{asset }}Contract()
            val e = assertThrows(RuntimeException::class.java) { contract.create{{asset }}(ctx, "1001", "{{assetSpaceSeparator }} 1001 value") }
            assertEquals(e.message, "The {{assetSpaceSeparator }} 1001 already exists")
        }

    }

    @Nested
    inner class read{{asset }} {

        @Test
        fun `should return a {{assetSpaceSeparator }}`() {
            val contract = {{asset }}Contract()
            val asset = contract.read{{asset }}(ctx, "1001")
            assertEquals("{{assetSpaceSeparator }} 1001 value", asset.value)
        }

        @Test
        fun `should throw an error for a {{assetSpaceSeparator }} that does not exist`() {
            val contract = {{asset }}Contract()
            val e = assertThrows(RuntimeException::class.java) { contract.read{{asset }}(ctx, "1003") }
            assertEquals(e.message, "The {{assetSpaceSeparator }} 1003 does not exist")
        }

    }

    @Nested
    inner class update{{asset }} {

        @Test
        fun `should update a {{assetSpaceSeparator }}`() {
            val contract = {{asset }}Contract()
            contract.update{{asset }}(ctx, "1001", "{{assetSpaceSeparator }} 1001 new value")
            verify(stub, times(1)).putState("1001", "{\"value\":\"{{assetSpaceSeparator }} 1001 new value\"}".toByteArray(Charsets.UTF_8))
        }

        @Test
        fun `should throw an error for a {{assetSpaceSeparator }} that does not exist`() {
            val contract = {{asset }}Contract()
            val e = assertThrows(RuntimeException::class.java) { contract.update{{asset }}(ctx, "1003", "{{assetSpaceSeparator }} 1003 new value") }
            assertEquals(e.message, "The {{assetSpaceSeparator }} 1003 does not exist")
        }

    }

    @Nested
    inner class delete{{asset }} {

        @Test
        fun `should delete a {{assetSpaceSeparator }}`() {
            val contract = {{asset }}Contract()
            contract.delete{{asset }}(ctx, "1001")
            verify(stub, times(1)).delState("1001")
        }

        @Test
        fun `should throw an error for a {{assetSpaceSeparator }} that does not exist`() {
            val contract = {{asset }}Contract()
            val e = assertThrows(RuntimeException::class.java) { contract.delete{{asset }}(ctx, "1003") }
            assertEquals(e.message, "The {{assetSpaceSeparator }} 1003 does not exist")
        }

    }

}

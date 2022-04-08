/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */

package main

import (
	"encoding/json"
	"errors"
	"fmt"
	"testing"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
	"github.com/hyperledger/fabric-chaincode-go/shim"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/mock"
)

const getStateError = "world state get error"

type MockStub struct {
	shim.ChaincodeStubInterface
	mock.Mock
}

func (ms *MockStub) GetState(key string) ([]byte, error) {
	args := ms.Called(key)

	return args.Get(0).([]byte), args.Error(1)
}

func (ms *MockStub) PutState(key string, value []byte) error {
	args := ms.Called(key, value)

	return args.Error(0)
}

func (ms *MockStub) DelState(key string) error {
	args := ms.Called(key)

	return args.Error(0)
}

type MockContext struct {
	contractapi.TransactionContextInterface
	mock.Mock
}

func (mc *MockContext) GetStub() shim.ChaincodeStubInterface {
	args := mc.Called()

	return args.Get(0).(*MockStub)
}

func configureStub() (*MockContext, *MockStub) {
	var nilBytes []byte

	test{{asset }} := new({{asset }})
	test{{asset }}.Value = "set value"
	{{assetCamelCase }}Bytes, _ := json.Marshal(test{{asset }})

	ms := new(MockStub)
	ms.On("GetState", "statebad").Return(nilBytes, errors.New(getStateError))
	ms.On("GetState", "missingkey").Return(nilBytes, nil)
	ms.On("GetState", "existingkey").Return([]byte("some value"), nil)
	ms.On("GetState", "{{assetCamelCase }}key").Return({{assetCamelCase }}Bytes, nil)
	ms.On("PutState", mock.AnythingOfType("string"), mock.AnythingOfType("[]uint8")).Return(nil)
	ms.On("DelState", mock.AnythingOfType("string")).Return(nil)

	mc := new(MockContext)
	mc.On("GetStub").Return(ms)

	return mc, ms
}

func Test{{asset }}Exists(t *testing.T) {
	var exists bool
	var err error

	ctx, _ := configureStub()
	c := new({{asset }}Contract)

	exists, err = c.{{asset }}Exists(ctx, "statebad")
	assert.EqualError(t, err, getStateError)
	assert.False(t, exists, "should return false on error")

	exists, err = c.{{asset }}Exists(ctx, "missingkey")
	assert.Nil(t, err, "should not return error when can read from world state but no value for key")
	assert.False(t, exists, "should return false when no value for key in world state")

	exists, err = c.{{asset }}Exists(ctx, "existingkey")
	assert.Nil(t, err, "should not return error when can read from world state and value exists for key")
	assert.True(t, exists, "should return true when value for key in world state")
}

func TestCreate{{asset }}(t *testing.T) {
	var err error

	ctx, stub := configureStub()
	c := new({{asset }}Contract)

	err = c.Create{{asset }}(ctx, "statebad", "some value")
	assert.EqualError(t, err, fmt.Sprintf("Could not read from world state. %s", getStateError), "should error when exists errors")

	err = c.Create{{asset }}(ctx, "existingkey", "some value")
	assert.EqualError(t, err, "The asset existingkey already exists", "should error when exists returns true")

	err = c.Create{{asset }}(ctx, "missingkey", "some value")
	stub.AssertCalled(t, "PutState", "missingkey", []byte("{\"value\":\"some value\"}"))
}

func TestRead{{asset }}(t *testing.T) {
	var {{assetCamelCase }} *{{asset }}
	var err error

	ctx, _ := configureStub()
	c := new({{asset }}Contract)

	{{assetCamelCase }}, err = c.Read{{asset }}(ctx, "statebad")
	assert.EqualError(t, err, fmt.Sprintf("Could not read from world state. %s", getStateError), "should error when exists errors when reading")
	assert.Nil(t, {{assetCamelCase }}, "should not return {{asset }} when exists errors when reading")

	{{assetCamelCase }}, err = c.Read{{asset }}(ctx, "missingkey")
	assert.EqualError(t, err, "The asset missingkey does not exist", "should error when exists returns true when reading")
	assert.Nil(t, {{assetCamelCase }}, "should not return {{asset }} when key does not exist in world state when reading")

	{{assetCamelCase }}, err = c.Read{{asset }}(ctx, "existingkey")
	assert.EqualError(t, err, "Could not unmarshal world state data to type {{asset }}", "should error when data in key is not {{asset }}")
	assert.Nil(t, {{assetCamelCase }}, "should not return {{asset }} when data in key is not of type {{asset }}")

	{{assetCamelCase }}, err = c.Read{{asset }}(ctx, "{{assetCamelCase }}key")
	expected{{asset }} := new({{asset }})
	expected{{asset }}.Value = "set value"
	assert.Nil(t, err, "should not return error when {{asset }} exists in world state when reading")
	assert.Equal(t, expected{{asset }}, {{assetCamelCase }}, "should return deserialized {{asset }} from world state")
}

func TestUpdate{{asset }}(t *testing.T) {
	var err error

	ctx, stub := configureStub()
	c := new({{asset }}Contract)

	err = c.Update{{asset }}(ctx, "statebad", "new value")
	assert.EqualError(t, err, fmt.Sprintf("Could not read from world state. %s", getStateError), "should error when exists errors when updating")

	err = c.Update{{asset }}(ctx, "missingkey", "new value")
	assert.EqualError(t, err, "The asset missingkey does not exist", "should error when exists returns true when updating")

	err = c.Update{{asset }}(ctx, "{{assetCamelCase }}key", "new value")
	expected{{asset }} := new({{asset }})
	expected{{asset }}.Value = "new value"
	expected{{asset }}Bytes, _ := json.Marshal(expected{{asset }})
	assert.Nil(t, err, "should not return error when {{asset }} exists in world state when updating")
	stub.AssertCalled(t, "PutState", "{{assetCamelCase }}key", expected{{asset }}Bytes)
}

func TestDelete{{asset }}(t *testing.T) {
	var err error

	ctx, stub := configureStub()
	c := new({{asset }}Contract)

	err = c.Delete{{asset }}(ctx, "statebad")
	assert.EqualError(t, err, fmt.Sprintf("Could not read from world state. %s", getStateError), "should error when exists errors")

	err = c.Delete{{asset }}(ctx, "missingkey")
	assert.EqualError(t, err, "The asset missingkey does not exist", "should error when exists returns true when deleting")

	err = c.Delete{{asset }}(ctx, "{{assetCamelCase }}key")
	assert.Nil(t, err, "should not return error when {{asset }} exists in world state when deleting")
	stub.AssertCalled(t, "DelState", "{{assetCamelCase }}key")
}

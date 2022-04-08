/*
 * {{spdxAndLicense // SPDX-License-Identifier: Apache-2.0 }}
 */

package main

import (
	"encoding/json"
	"fmt"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// {{asset }}Contract contract for managing CRUD for {{asset }}
type {{asset }}Contract struct {
	contractapi.Contract
}

// {{asset }}Exists returns true when asset with given ID exists in world state
func (c *{{asset }}Contract) {{asset }}Exists(ctx contractapi.TransactionContextInterface, {{assetCamelCase }}ID string) (bool, error) {
	data, err := ctx.GetStub().GetState({{assetCamelCase }}ID)

	if err != nil {
		return false, err
	}

	return data != nil, nil
}

// Create{{asset }} creates a new instance of {{asset }}
func (c *{{asset }}Contract) Create{{asset }}(ctx contractapi.TransactionContextInterface, {{assetCamelCase }}ID string, value string) error {
	exists, err := c.{{asset }}Exists(ctx, {{assetCamelCase }}ID)
	if err != nil {
		return fmt.Errorf("Could not read from world state. %s", err)
	} else if exists {
		return fmt.Errorf("The asset %s already exists", {{assetCamelCase }}ID)
	}

	{{assetCamelCase }} := new({{asset }})
	{{assetCamelCase }}.Value = value

	bytes, _ := json.Marshal({{assetCamelCase }})

	return ctx.GetStub().PutState({{assetCamelCase }}ID, bytes)
}

// Read{{asset }} retrieves an instance of {{asset }} from the world state
func (c *{{asset }}Contract) Read{{asset }}(ctx contractapi.TransactionContextInterface, {{assetCamelCase }}ID string) (*{{asset }}, error) {
	exists, err := c.{{asset }}Exists(ctx, {{assetCamelCase }}ID)
	if err != nil {
		return nil, fmt.Errorf("Could not read from world state. %s", err)
	} else if !exists {
		return nil, fmt.Errorf("The asset %s does not exist", {{assetCamelCase }}ID)
	}

	bytes, _ := ctx.GetStub().GetState({{assetCamelCase }}ID)

	{{assetCamelCase }} := new({{asset }})

	err = json.Unmarshal(bytes, {{assetCamelCase }})

	if err != nil {
		return nil, fmt.Errorf("Could not unmarshal world state data to type {{asset }}")
	}

	return {{assetCamelCase }}, nil
}

// Update{{asset }} retrieves an instance of {{asset }} from the world state and updates its value
func (c *{{asset }}Contract) Update{{asset }}(ctx contractapi.TransactionContextInterface, {{assetCamelCase }}ID string, newValue string) error {
	exists, err := c.{{asset }}Exists(ctx, {{assetCamelCase }}ID)
	if err != nil {
		return fmt.Errorf("Could not read from world state. %s", err)
	} else if !exists {
		return fmt.Errorf("The asset %s does not exist", {{assetCamelCase }}ID)
	}

	{{assetCamelCase }} := new({{asset }})
	{{assetCamelCase }}.Value = newValue

	bytes, _ := json.Marshal({{assetCamelCase }})

	return ctx.GetStub().PutState({{assetCamelCase }}ID, bytes)
}

// Delete{{asset }} deletes an instance of {{asset }} from the world state
func (c *{{asset }}Contract) Delete{{asset }}(ctx contractapi.TransactionContextInterface, {{assetCamelCase }}ID string) error {
	exists, err := c.{{asset }}Exists(ctx, {{assetCamelCase }}ID)
	if err != nil {
		return fmt.Errorf("Could not read from world state. %s", err)
	} else if !exists {
		return fmt.Errorf("The asset %s does not exist", {{assetCamelCase }}ID)
	}

	return ctx.GetStub().DelState({{assetCamelCase }}ID)
}

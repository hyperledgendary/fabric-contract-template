# Hyperledger Fabric Smart Contract Templates

This is a basic Smart Contract Template from which to create your own templates.

This template is designed to run with [Copier v6](https://copier.readthedocs.io/en/latest/)

```
copier https://github.com/hyperledgendary/fabric-contract-template.git MyNewContract
```


# Not got copier?

```bash
python3 -m pip install --user pipx
python3 -m pipx ensurepath

pipx install copier && pipx inject copier jinja2-strcase
```
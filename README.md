# Tile Schemas

This repository contains JSON Schemas for describing tiles, their deployment
metadata, and request formats. The schemas are provided for anyone who needs
a structured way to define and validate tile-related configuration.

## Contents

- `schemas/tile-deployment.schema.json`  
  Defines how a tile declares its identity, version, capabilities, and runtime
  requirements.

- `schemas/tile-config.schema.json`  
  Defines how a tile is invoked in a configuration or workflow, including which
  request to call and what parameters to provide.

- `schemas/tile-request.schema.json`  
  Defines the structure of a runtime request sent to a tile.

- `schemas/tile-request-param-value.schema.json`  
  Defines a parameter value used in a tile request.

## Usage

These are standard JSON Schema files. You can use them with any tool or
framework that understands JSON Schema.

### Java

One common approach is to generate Java model classes using
[jsonschema2pojo](https://www.jsonschema2pojo.org/). Example:

    ./gradle generateJsonSchema2Pojo

Configure the plugin to point at this repository or at specific schema files.

### Python

For Python projects, you can generate Pydantic models or dataclasses using
[datamodel-code-generator](https://koxudaxi.github.io/datamodel-code-generator/).
Example:

    datamodel-codegen --input tile-deployment.schema.json --output models.py

### Other languages

Any JSON Schema code generation or validation tool can be used. If your
language of choice has support for JSON Schema, you should be able to apply it
to these files.

## License

This repository is licensed under the MIT License. See the [LICENSE](LICENSE)
file for details.

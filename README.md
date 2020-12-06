# QAQ Backend

![Format](https://github.com/SEQAQ/backend/workflows/Format/badge.svg)
![Tests](https://github.com/SEQAQ/backend/workflows/Tests/badge.svg)
![Build and Deploy](https://github.com/SEQAQ/backend/workflows/Build%20and%20Deploy/badge.svg)

Backend for the QAQ

## Getting Started

### Spring Profiles

Create `application-local.properties` in `src/main/resources` and set active profile in IDE/commandline arguments.

### Test Environments

```shell script
# Start the test env
docker-compose -f docker-compose-citest.yml up --build
# reset test env
docker-compose -f docker-compose-citest.yml down
```

Refer to [config](docker-compose-citest.yml) to see the config for test environment.

Update the [initial MySQL Script](scripts/mysql/1_init_data.sql) if necessary for tests.

### Code Format

We follow [Google Java Format](https://github.com/google/google-java-format)

A [GitHub Action](https://github.com/axel-op/googlejavaformat-action) will also format all files.

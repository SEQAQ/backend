# QAQ Backend
Backend for the QAQ

## Getting Started

### Spring Profiles

Create `application-local.properties` in `src/main/resources` and set active profile in IDE/commandline arguments.

### Test Environments

```shell script
docker-compose -f docker-compose-citest.yml up --build
```

Refer to [config](docker-compose-citest.yml) to see the config for test environment.

Update the [initial MySQL Script](scripts/mysql/1_init_data.sql) if necessary for tests.

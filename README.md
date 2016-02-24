# Tube

Generic, configurable radiator software

## Start the server with example configuration

```
lein run -fdocs/config-draft.json
```

## Send some data to it

```
curl -X POST -d '{"orders": 44}' http://localhost:8200/api/parameter-update --header "Content-Type:application/json"
```

$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/api/GetPet.feature");
formatter.feature({
  "name": "Getting and deserializing pet from petstore",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "get pet",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@pet"
    }
  ]
});
formatter.step({
  "name": "contentType head is set to \"application/json\"",
  "keyword": "Given "
});
formatter.step({
  "name": "user executes \"GET\" request",
  "keyword": "When "
});
formatter.step({
  "name": "the status code is OK",
  "keyword": "Then "
});
formatter.step({
  "name": "contentType header is \"application/json\"",
  "keyword": "And "
});
formatter.step({
  "name": "users verified \u003cid\u003e \u003cname\u003e \u003ctags\u003e size",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "id",
        "name",
        "tags"
      ]
    },
    {
      "cells": [
        "10",
        "doggie",
        "1"
      ]
    }
  ]
});
formatter.scenario({
  "name": "get pet",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@pet"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "contentType head is set to \"application/json\"",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitions.API.PetStoreSteps.contenttype_head_is_set_to(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "user executes \"GET\" request",
  "keyword": "When "
});
formatter.match({
  "location": "StepDefinitions.API.PetStoreSteps.user_executes_GET_request(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "the status code is OK",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.afterstep({
  "status": "passed"
});
formatter.beforestep({
  "status": "skipped"
});
formatter.step({
  "name": "contentType header is \"application/json\"",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefinitions.API.PetStoreSteps.contenttype_header_is(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.afterstep({
  "status": "skipped"
});
formatter.beforestep({
  "status": "skipped"
});
formatter.step({
  "name": "users verified 10 doggie 1 size",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.afterstep({
  "status": "skipped"
});
formatter.after({
  "status": "passed"
});
});
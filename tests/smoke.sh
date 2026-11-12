#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
TMP=$(mktemp -d)
trap 'rm -rf "$TMP"' EXIT
javac -encoding UTF-8 -d "$TMP" src/dominio/*.java src/Main.java tests/DomainSmokeTest.java
java -cp "$TMP" DomainSmokeTest
java -cp "$TMP" Main >/dev/null

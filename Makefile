.PHONY: test
test:
	semgrep --validate --config=./rules ./rules
	semgrep --validate --strict --config=./rules ./rules
	semgrep --test ./rules
	semgrep --test --strict ./rules

.PHONY: output
output:
	semgrep --test --strict --save-test-output-tar ./rules

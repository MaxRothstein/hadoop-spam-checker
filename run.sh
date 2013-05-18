#!/bin/bash

set -e

IN_PATH=${1:-spamcheck_in}
OUT_PATH=${2:-spamcheck_out}
OUT_PATH_LOCAL=${3:-output}

rm ${OUT_PATH_LOCAL}/$OUT_PATH || true
hadoop fs -rmr $OUT_PATH || true
hadoop jar target/HadoopSpamCheck-1.0.jar edu.cooper.ece460.spamcheck.HadoopSpamCheck $IN_PATH $OUT_PATH
hadoop fs -getmerge $OUT_PATH ${OUT_PATH_LOCAL}/$OUT_PATH
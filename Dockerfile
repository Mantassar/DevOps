FROM ubuntu:latest
LABEL authors="monta"

ENTRYPOINT ["top", "-b"]
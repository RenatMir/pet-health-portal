#!/bin/bash

cd "$(dirname "$0")" || exit

prompt_yes_no() {
  while true; do
    read -p "$1 (y/n): " -r
    case $REPLY in
      [Yy]* ) return 0;;
      [Nn]* ) return 1;;
      * ) echo "Please answer y or n.";;
    esac
  done
}

if prompt_yes_no "Start containers?"; then
  if prompt_yes_no "Clear volumes before starting?"; then
    echo "Clearing volumes..."
    docker compose down -v
  fi
  echo "Starting containers..."
  docker compose up -d
else
  if prompt_yes_no "Stop containers?"; then
    if prompt_yes_no "Clear volumes after stopping?"; then
      echo "Stopping containers and clearing volumes..."
      docker compose down -v
    else
      echo "Stopping containers..."
      docker compose down
    fi
  else
    echo "No action taken. Exiting."
    exit 0
  fi
fi

echo "Done!"

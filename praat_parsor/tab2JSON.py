#!/usr/bin/env python3
"""
  Script for parsing tabbed files
"""
from collections import OrderedDict
import re
import glob
import argparse
import json


""""
  Script Configurations
"""

DELIMINER = "\t"  # Specify separator
IGNORE_FIRST_LINE = False  # Ignore first line if its a header

# List of Variables in order with their respective types
VARIABLENAMES = OrderedDict({
    'key_child': 'boolean',
    'other_children': 'boolean',
    'number_of_other_children': 'number',
    'mother': 'bool',
    'other_female_adults': 'bool',
    'number_of_other_female_adults': 'number',
    'other_male_adults': 'bool',
    'number_of_other_male_adults': 'number',
    'who_were_they_talking_to': 'text',
    'actions': 'text',
    'comments': 'text',
    'other_researchers_and_students': 'bool',
    'research_participants': 'bool',
    'anyone': 'bool'
})

FILENAME_PATTERN = "*_ac.txt"  #  Pattern of the file names

# Pattern of the files with rule to extract name
EXTRACT_FILENAME_PATTERN = r"(.*)_ac.txt"

INPUT_FILETYPE = ".wav"  # The types of files to describe

"""
 Data Types & Convertion
"""


def strToText(text):
    return text


def strToBoolean(boolean):
    if "true" in boolean.lower():
        return True
    elif "1" in boolean.lower():
        return True
    else:
        return False


def strToNumber(number):
    try:
        if "." in number:
            return float(number)
        else:
            return int(number)
    except ValueError:
        return 0


def convertToData(s, type):
    if "text" in type:
        return strToText(s)
    elif "number" in type:
        return strToNumber(s)
    elif "bool" in type:
        return strToBoolean(s)


"""
  Parsor
"""


def tokenise(items):
    """ Tokenise a list of items from the Global Variable array"""
    result = {}
    counter = 0
    if len(VARIABLENAMES.keys()) == len(items):
        for entryName, entryType in VARIABLENAMES.items():
            result[entryName] = convertToData(items[counter], entryType)
            counter += 1
        return result
    else:
        print("File format does not correspond to GRAMAR")
        return {}


def fileToEntries(filename):
    """ Create a list of Entrie from a tabbed File """
    found = re.search(EXTRACT_FILENAME_PATTERN, filename)
    if found:
        wavFilename = found.group(1) + INPUT_FILETYPE
        resultEntry = []
        with open(filename) as fd:
            for line in fd:
                items = line.split(DELIMINER)
                resultEntry.append(tokenise(items))
            return wavFilename, resultEntry
    else:
        print("Filename {" + filename +
              "} does not match pattern {" + FILENAME_PATTERN + "}")
        return '', {}


"""
 Append all info files into one dictionairy sorted by filename
"""


def appendMultipleTabFiles(folder):
    result = {}
    location = "{}/{}".format(folder, FILENAME_PATTERN)
    for filename in glob.iglob(location):
        wavFilename, Entries = fileToEntries(filename)
        result[wavFilename] = Entries
    return result


"""
 Main
"""

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Image Downloader')
    parser.add_argument('folder', type=str, help='Input folder')
    parser.add_argument('output', type=str, help='Output file')
    args = parser.parse_args()

    if args.folder and args.output:
        result = appendMultipleTabFiles(args.folder)
        with open("{}.json".format(args.output), 'w') as fd:
            json.dump(result, fd)

## Generic Parsor for Tab separated files

This is a script that parses a list of tab separated data files and appends them into a JSON data object


The script can be adapted by editing the configuration variables at the top.

1. VARIABLENAMES : the list of the column names & their types (current types supported : number, text, boolean).

2. DELIMINER : can alter the deliminer used in the file.

3. FILENAME_PATTERN : specifies the files that are going to be used.

4. EXTRACT_FILENAME_PATTERN : used to extract the filename from the extension.

5. INPUT_FILETYPE : specified the extension of the file described by the data file.

## Use

1. `./tab2JSON.py <input_folder> <output_filename>`

## Dependencies

Python 3 needs to be installed (might work with 2.7 but not tested).


## Sample output structure

```json
{
  "/path/to/file/filename.wav": [
    {
      <columns dictionary>
    }
  ],
  "/path/to/file/filename.wav": [
    {
       <columns dictionary>
    }
  ]
}
```

input & output samples in the data folder.


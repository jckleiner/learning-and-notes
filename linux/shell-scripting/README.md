# Shell Scripting

## Table of Contents
  1. [Coding Conventions](#coding-conventions)
  2. [Parsing Commandline Options With `getopts` / `getopt`](#parsing-commandline-options)
  2. [Basics](#basics)

<details id="coding-conventions">
<summary><b>Coding Conventions</b></summary>
<br/>

Although they are followed by everyone, here are still some conventions from [Google's Shell Style Guide](https://google.github.io/styleguide/shellguide.html#s7-naming-conventions).

**Variable Names**: Lower-case, with underscores to separate words. Ex: `my_variable_name`

**Constants and Environment Variable Names**: All caps, separated with underscores, declared at the top of the file. Ex: `MY_CONSTANT`

**TODO** others like functions, if-else, switch, etc.

Well, you asked for it!
</details>

<br/>

<details id="parsing-commandline-options">
<summary><b>Parsing Commandline Options With </b><code>getopts</code> / <code>getopt</code></summary>
<br/>

`getopts` is a built-in Unix shell command for parsing command-line arguments. It is designed to process command line arguments that follow the POSIX Utility Syntax Guidelines, based on the C interface of getopt. 
The predecessor to getopts was the external program `getopt` by Unix System Laboratories. 

`getopt` and `getopts` are different beasts, and people seem to have a bit of misunderstanding of what they do. `getopts` is a built-in command to `bash` to process command-line options in a loop and assign each found option and value in turn to built-in variables, so you can further process them. `getopt`, however, is an external utility program, and it doesn't actually process your options for you the way that e.g. bash getopts, the Perl Getopt module or the Python optparse/argparse modules do. All that `getopt` does is canonicalize the options that are passed in — i.e. convert them to a more standard form, so that it's easier for a shell script to process them. For example, an application of `getopt` might convert the following:

  * `myscript -ab infile.txt -ooutfile.txt`

into this:

  * `myscript -a -b -o outfile.txt infile.txt`

You have to do the actual processing yourself.

**Why use `getopt` instead of `getopts`?** 

The basic reason is that only GNU `getopt` gives you support for long-named command-line options (short: `-d` long: `--delete`). (GNU `getopt` is the default on Linux. Mac OS X and FreeBSD come with a basic and not-very-useful `getopt`, but the GNU version can be installed.)

  * Bash builtin `getopts`. This does not support long option names with the double-dash prefix. It only supports single-character options.
  * BSD UNIX implementation of standalone `getopt` command (which is what MacOS uses). This does not support long options either.
  * GNU implementation of standalone `getopt`. GNU getopt(3) (used by the command-line getopt(1) on Linux) supports parsing long options.

### Simple Example for `getopts`

```bash
while getopts ":h:t" opt; do
    case ${opt} in
        h ) # process option h
            echo "option: 'h' with arg: $OPTARG"
            ;;
        t ) # process option t
            echo "option: 't'"
            ;;
        \? ) 
            echo "Usage: cmd [-h] [-t]"
            ;;
    esac
done
```
  * a `:` after the option means it requires the argument.
  * if an invalid option is provided, the option variable is assigned the value `?`, this behaviour is only true when you prepend the list of valid options with : to disable the default error handling of invalid options. It is recommended to always disable the default error handling in your scripts. Although not sure why this is recommended since the default error handler seems to be useful:
    * With preceding `:` -> `./demo.sh -t -h` prints 
    
            option: 't'
    
    * Without preceeding `:` -> `./demo.sh -t -h` prints

            option: 't' 
            ./demo.sh: option requires an argument -- h
            Usage: cmd [-h] [-t]
    **TODO**: how to do proper error handling for arguments?

  * Without error checking, giving the arguments in the wrong order can lead to unexpected behavior. For example `./demo.sh -h -t` gives `option: 'h' with arg: -t`


</details>
<br/>

<details id="basics">
<summary><b>Basics</b></summary>
<br/>

**Important**

For variables and functions to be evaluated inside strings, use `"..."` and NOT `'...'`
**TODO** sure?

</details>
<br/>

**TODO**
  * Zefuk is AD?
  * What does SHIFT + CMD + H do in Iterm?
  * Jesse Skelton, other videos are also cool - https://www.youtube.com/channel/UC7syy0V3Ah9Ho4eRUCwRsRg
  * Yabai - Tiling Window Manager for Mac [ Tutorial ] - https://www.youtube.com/watch?v=JL1lz77YbUE
  * After a Minimal Linux Install: Graphical Envionment and Users - https://www.youtube.com/watch?v=nSHOb8YU9Gw
  * slow quit apps?
  * thor? Terminal?
  * when git checkout commit, it doesnt show in terminal
  * ADD: gss and commig with fzf
  * ADD: make squash faster?
  * ADD: to prompt, committed but not pushed, or how many commits up/down from remote
  * ADD: to prompt, bold black line instead of grey, maybe underline?
  * What is POSIX?
  * What is POSIX complient OS/Shells?
  * Bash if [[ == ]] glob matching? -> program 'shellcheck' can say posix complience2
  * Unix, Linux, BSD, Mac are all mostly* POSIX complient? 
    They are implemented tot>ally differently under the hood but yet they look so similar because posix complience?



# Golang

VsCode shows the following hints:

    The "gopls" command is not available. Run "go install -v golang.org/x/tools/gopls@latest" to install.`
    The "godef" command is not available. Run "go install -v github.com/rogpeppe/godef@latest" to install.
    The "goimports" command is not available. Run "go install -v golang.org/x/tools/cmd/goimports@latest" to install.
    The "go-outline" command is not available. Run "go install -v github.com/ramya-rao-a/go-outline@latest" to install.
    The "gocode-gomod" command is not available. Run "go install -v github.com/stamblerre/gocode@latest" to install.

 * `gopls`: Language server
 * `godef`: given an expression or a location in a source file, prints the location of the definition of the symbol referred to.
 * `goimports`: Command goimports updates your Go import lines, adding missing ones and removing unreferenced ones. 
 * `go-outline`: Simple utility for extracting a JSON representation of the declarations in a Go source file.


Sources:
 * https://www.youtube.com/watch?v=sf7f4QGkwfE

Every `.go` file has to declare a `package`

## Package Structure
Packages in go are organized across files as apposed to other languages.
Which means a package is composed of multiple files and multiple files compose a package.

2 Package types: 
 1. main (executable) packages
 2. non-main packages

The entry point of your program is the `main` function and it must be located inside the `main` pacakge.
Non-main packages provide functionality which can be imported and used in the main package or in other packages.
You cannot compile or execute non-main packages.

### Best Practices
 * Your directory name and your package name should be the same. If you have a package `network`, your directory should also be named `network`
 * Names should referably be one word, no camelCase or underscores
 * Keep the packages as flat as possible by avoiding a lot of nested folders


### GOPATH
The root of every go project. 
Inside it you have the following directories:
 - `bin`: all the binaries
 - `source`: where all your go code will be stored
 - `pkg`: where all the 3rd party packages are downloaded

### Cross Reference
Go does not allow cross references for packages. If package `a` references package `b`, package `b` is then not allowed to reference package `a`

### File Globals and File Locals
Following are file specific, not shared across files. Which means every file has to declare them for itself  :
 * package name
 * imports

Globals are declarations which you have to declare once inside at least one file
and it's going to be available inside every file of the same package.
 * constants
 * variables
 * functions

### Package Visibility (Scope)
 * Exported: Visible and accessible in all files of the same package and outside the package
 * unExported (private): Visible and accessible inside all files of the same package but not accessible outside of it

### Special Directories
 * vendor
 * internal
 * pkg
 * cmd
 * .dir
 * _dir
 * testdata
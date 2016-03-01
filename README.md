# N-ary tree implementation for java

## About

-- draft -- 8< -- draft -- 8< -- draft --

Proof of concept of generic n-tree library implementation. 
WARNING: it is still < v.1.0.0 - API will change

What works now: 
* NTree interface 
.* defines basic operations on tree: add, remove, find, get number of children of an node, etc.
.* two-argument generic: one for data which node can hold and second one for node itself
* NodeNTree interface that extends and simplifies NTree interface by declaring node interface; generic with only one argument which is just node data
* DataNTree (temporary name) - implementation of NodeNTree interface
* Ready to use decorator to enforce any NTree instance to be read-only (note: read-only != immutable)
* Iterators to define common tree walk algorithms: pre-order, post-order and level-order (see https://en.wikipedia.org/wiki/Tree_traversal for details).
* Algorithms: basic tree converter,
* Basic unit tests 

What will be changed next:
* NTreeNode API is pretty random and messy
* More algorithms in NTrees class (merging trees, cutting subtrees from tree etc.)
* Subtree views
* Improved tests with 100% of code coverage
* Trees converter should be optimized for memory usage
* Better examples
* ? Move generic code like ConvertingIterable to utils lib

-- draft -- 8< -- draft -- 8< -- draft --

## Examples

1. Simple example
1. UI Example
//TODO

## Usage

//TODO

## Example

//TODO

## License

http://www.apache.org/licenses/LICENSE-2.0

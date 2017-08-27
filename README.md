# svd-compression

This document will provide details regarding the web application svd-compression, providing details on the application summary, details of files, terminology, as well as technical details regarding the development of the application.

## Getting Started
svd-compression can be cloned using both HTTPS and SSH via git commands.

### Prerequisites

To download and be able to make edits to the svd-compression project you will need to install git [here](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

### HTTPS

Navigate in your terminal/command-prompt to the folder in which you would like to store the application.

In your terminal/command-prompt type in `git clone https://github.com/jkys/svd-compression.git` and press `enter` or `return` to start downloading all the files into the select folder.

### SSH

Navigate in your terminal/command-prompt to the folder in which you would like to store the application.

In your terminal/command-prompt type in `git clone git@github.com:jkys/svd-compression.git` and press `enter` or `return` to start downloading all the files into the select folder.

## Summary

svd-compression is a Java program which can take an image as input, convert it into a matrix of values representing each pixel, and the perform operations on that matrix to be able to compress the image using the singular value decomposition linear algebra technique. This project is configurable to be able to shrink the image as much as possible and process multiple outputs to be able to show the difference in quality after each compression. This works on both images with color and without.

The project will out put four different images, one named "imageUpdated", and the others named "S", "U", and "V" relating to the different matrices computed to be able to shrink down the original image. The "S", "U", and "V" matrices are used to multiply by each other with some other translations added to be able to produce the "imageUpdated" resulting image.

## Built With

* [Apache Commons Math](http://commons.apache.org/proper/commons-math/) - For a reliable polynomial solver and matrix library
* [JAMA](http://math.nist.gov/javanumerics/jama/) - For singular value decomposition and matrix library.

## Built In

* Java

## Authors

* [Jonathan Keys](https://github.com/jkys) - Initial work/maintenance

See also the list of [contributors](https://github.com/jkys/svd-compression/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/jkys/svd-compression/blob/master/LICENSE.md) file for details

## Acknowledgments

* Thank you for the wonderful developers at Apache and JAMA for their great libraries for computational classes and methods dealing with polynomials, matrices, eigen vectors/matrices, and the svd algorithm itself.

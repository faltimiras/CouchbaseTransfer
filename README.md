## CouchbaseTransfer
Insert documents to Couchbase from a file or from command line

3 operation:
**-cl**: Insert document to CouchBase from command line

`cb-cli -cl -h <HOST> -b <BUCKET> -p <PASSWORD?> -k <KEY> -d <DOCUMENT?>`

if document is not defined with -d, command line will wait to be writen in the command line. Useful with some consoles that removes some '"'.

**-wf**:  Write document to a file to be inserted to multiple hosts

`cb-cli -wf -f <FILENAME> -b <BUCKET> -p <PASSWORD?> -k <KEY> -d <DOCUMENT> -x`

**-ff**: Insert documents to Couchbase from file

`cb-cli -ff -h <HOST> -f <FILENAME>`

XML file example:
```xml
<fileEntries>
    <entries>
        <fileEntry bucketName="bucketName">
            <docs>
                <doc failIfPresent="false" key="key">{"hola":"adeu"}</doc>
            </docs>
        </fileEntry>
    </entries>
</fileEntries>
```
You can add manually documents to this file or use the operation **-wf** to do it


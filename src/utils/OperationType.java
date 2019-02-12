package utils;

public enum OperationType {
    //VCS commands
    STATUS,
    BRANCH,
    COMMIT,
    CHECKOUT,
    LOG,
    ROLLBACK,
    VCS_INVALID_OPERATION,

    //Filesystem commands
    CAT,
    CHANGEDIR,
    LIST,
    MAKEDIR,
    REMOVE,
    TOUCH,
    WRITETOFILE,
    PRINT,
    FILESYSTEM_INVALID_OPERATION
}

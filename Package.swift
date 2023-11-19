// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/VitaSokolova/KMP-shared/com/vita/sokolova/shared-kmmbridge/0.1.4/shared-kmmbridge-0.1.4.zip"
let remoteKotlinChecksum = "7764a80f0a446f0286d24bda38f5d6f3c9cf7cb09b1713a488aca7f49626eb93"
let packageName = "shared"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)
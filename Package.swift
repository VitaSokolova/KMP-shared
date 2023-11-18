// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/VitaSokolova/KMP-shared/me/sokolovavita/kmp-shared/shared-kmmbridge/1.0.0/shared-kmmbridge-1.0.0.zip"
let remoteKotlinChecksum = "f6e624a4b02b5113fb43a70bb3ee8d18f2bcabba3fc7d6c83968b498e4dbd768"
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
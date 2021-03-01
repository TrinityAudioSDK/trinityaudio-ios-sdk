// swift-tools-version:5.3
// The swift-tools-version declares the minimum version of Swift required to build this package.
import PackageDescription

let package = Package(
    name: "TrinityAudioSDK",
    products: [
        .library(
            name: "TrinityAudioSDK",
            targets: ["TrinityAudioSDK"]),
    ],
    dependencies: [
    ],
    targets: [
        .binaryTarget(
            name: "TrinityAudioSDK",
            path: "TrinityPlayer.xcframework")
    ]
)

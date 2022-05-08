// swift-tools-version:5.3
// The swift-tools-version declares the minimum version of Swift required to build this package.
import PackageDescription

let package = Package(
    name: "TrinityPlayer",
    products: [
        .library(
            name: "TrinityPlayer",
            targets: ["TrinityPlayer"]),
    ],
    dependencies: [
    ],
    targets: [
        .binaryTarget(
            name: "TrinityPlayer",
            path: "TrinityPlayer.xcframework")
    ]
)

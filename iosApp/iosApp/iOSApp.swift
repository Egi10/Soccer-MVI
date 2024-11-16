import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    init() {
        CoreApplication().initialize(appDeclaration: {_ in })
    }

    var body: some Scene {
        WindowGroup {
            ComposeView(
                root: appDelegate.root
            ).ignoresSafeArea()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    lazy var root: RootComponent = DependencyInjection.shared.rootComponent(
        componentContext: DefaultComponentContext(
            lifecycle: ApplicationLifecycle()
        )
    )

    func application(_ application: UIApplication, shouldSaveSecureApplicationState coder: NSCoder) -> Bool {
        return true
    }

    func application(_ application: UIApplication, shouldRestoreSecureApplicationState coder: NSCoder) -> Bool {
        return true
    }
}

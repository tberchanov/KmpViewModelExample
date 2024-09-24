import SwiftUI
import Shared

struct ContentView: View {

    let component: DiComponent

    init() {
        DiHelperKt.setupDi()
        component = DiComponent()
    }

    @State private var showContent = false
    @State private var counter: Int = 0

    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                    Text("Counter: \(counter)")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
        .onChange(of: showContent) { newValue in
            if !newValue {
                component.clearExampleViewModel(key: "ExampleVM")
            } else {
                component.exampleViewModel(key: "ExampleVM").startCounter()
                Task {
                    for await counter in component.exampleViewModel(key: "ExampleVM").counterFlow {
                        self.counter = Int(truncating: counter)
                    }
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
